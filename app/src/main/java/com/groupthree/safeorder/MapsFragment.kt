package com.groupthree.safeorder

import android.Manifest
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.graphics.Canvas
import android.location.Location
import androidx.fragment.app.Fragment

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import com.google.android.libraries.maps.CameraUpdateFactory
import com.google.android.libraries.maps.GoogleMap
import com.google.android.libraries.maps.OnMapReadyCallback
import com.google.android.libraries.maps.SupportMapFragment
import com.google.android.libraries.maps.model.*
import com.groupthree.safeorder.database.Restaurant
import com.groupthree.safeorder.database.RestaurantViewModel
import com.groupthree.safeorder.database.RestaurantViewModelFactory
import com.groupthree.safeorder.database.SafeOrderDB
import com.groupthree.safeorder.databinding.MapsBinding

class MapsFragment : Fragment() {
    private var map : GoogleMap? = null
    private var cameraPosition : CameraPosition? = null
    private var locationPermissionGranted = false
    private lateinit var fusedLocationProviderClient : FusedLocationProviderClient
    private var lastKnownLocation : Location? = null
    private val defaultLocation = LatLng(51.0230052,7.5610071)
    private var resList : List<Restaurant>? = null
    private val callback = OnMapReadyCallback { googleMap ->
        map = googleMap

        getLocationPermission()
        updateLocationUI()
        getDeviceLocation()
        addLocations()
    }

    private fun getLatLng(coords : String) : LatLng {
        val array = coords.split(";")
        return LatLng(array[0].toDouble(), array[1].toDouble())
    }

    private fun addLocations() {
        //Get locations from Database
        if (resList != null) {
            for (restaurant : Restaurant in resList!!) {
                val coords = getLatLng(restaurant.address.coordinates)
                val marker = map?.addMarker(MarkerOptions().
                    position(coords).
                    title(restaurant.restaurantName).
                    snippet("${restaurant.address.street} ${restaurant.address.number}"))
                marker?.tag = restaurant.restaurantID
                marker?.showInfoWindow()
            }
        }

        /*
        val ottimo = LatLng(50.98653,7.4092875)
        val marker = map?.addMarker(MarkerOptions().
                position(ottimo).
                title("Ottimo").
                snippet("Restaurant").
                icon(getBitmap(R.drawable.ic_favorite_white_24dp)))
        marker?.tag = 1 // ID from database
        marker?.showInfoWindow()
        */

        map?.setOnMarkerClickListener { marker ->
            val id = marker.tag as? Int
            Toast.makeText(context, "${marker.title} has been clicked.", Toast.LENGTH_SHORT).show()
            val bundleID = Bundle()
            bundleID.putInt("res_id", id!!)
            val nav= findNavController()
            nav.navigate(R.id.restaurantProfileFragment, bundleID)
            return@setOnMarkerClickListener false
        }

    }

    private fun getBitmap(drawableRes : Int) : BitmapDescriptor {
        val drawable = ContextCompat.getDrawable(requireActivity(), drawableRes)
        val canvas = Canvas()
        val bitmap = Bitmap.createBitmap(drawable?.intrinsicWidth!!, drawable.intrinsicHeight, Bitmap.Config.ARGB_8888)
        canvas.setBitmap(bitmap)
        drawable.setBounds(0, 0, drawable.intrinsicWidth, drawable.intrinsicHeight)
        drawable.draw(canvas)
        return BitmapDescriptorFactory.fromBitmap(bitmap)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val binding = MapsBinding.inflate(inflater)

        val app = requireActivity().application
        val dataSource = SafeOrderDB.getDatabase(requireContext()).restaurantDAO()
        val viewModelFactory = RestaurantViewModelFactory(dataSource, app)
        val restaurantViewModel = ViewModelProvider(this, viewModelFactory).get(RestaurantViewModel::class.java)
        binding.lifecycleOwner = this
        //binding.restaurantViewModel = restaurantViewModel
        resList = restaurantViewModel.getRestaurants()

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        if (savedInstanceState != null) {
            lastKnownLocation = savedInstanceState.getParcelable(KEY_LOCATION)
            cameraPosition = savedInstanceState.getParcelable(KEY_CAMERA_POSITION)
        }

        fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(requireContext())

        val mapFragment = childFragmentManager.findFragmentById(R.id.map) as SupportMapFragment?
        mapFragment?.getMapAsync(callback)
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        map?.let { map ->
            outState.putParcelable(KEY_CAMERA_POSITION, map.cameraPosition)
            outState.putParcelable(KEY_LOCATION, lastKnownLocation)
        }
    }

    private fun getDeviceLocation() {
        try {
            if (locationPermissionGranted) {
                val locationResult = fusedLocationProviderClient.lastLocation
                locationResult.addOnCompleteListener(requireActivity()) { task ->
                    if (task.isSuccessful) {
                        lastKnownLocation = task.result
                        if (lastKnownLocation != null) {
                            map?.moveCamera(
                                CameraUpdateFactory.newLatLngZoom(
                                LatLng(lastKnownLocation!!.latitude,
                                    lastKnownLocation!!.longitude), DEFAULT_ZOOM.toFloat()
                            ))
                        }
                    } else {
                        Log.d(TAG, "Current location is null. Using defaults.")
                        Log.e(TAG, "Exception: %s", task.exception)
                        map?.moveCamera(CameraUpdateFactory.newLatLngZoom(defaultLocation, DEFAULT_ZOOM.toFloat()))
                        map?.uiSettings?.isMyLocationButtonEnabled = false
                    }
                }
            }
        } catch (e : SecurityException) {
            Log.e("Exception: %s", e.message, e)
        }
    }

    private fun getLocationPermission() {
        if (ContextCompat.checkSelfPermission(requireContext().applicationContext, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
            locationPermissionGranted = true
        } else {
            ActivityCompat.requestPermissions(requireActivity(), arrayOf(Manifest.permission.ACCESS_FINE_LOCATION), PERMISSIONS_REQUEST_ACCESS_FINE_LOCATION)
        }
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        locationPermissionGranted = false
        when (requestCode) {
            PERMISSIONS_REQUEST_ACCESS_FINE_LOCATION -> {
                if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    locationPermissionGranted = true
                }
            }
        }
        updateLocationUI()
    }

    private fun updateLocationUI() {
        if (map == null) {
            return
        }
        try {
            if (locationPermissionGranted) {
                map?.isMyLocationEnabled = true
                map?.uiSettings?.isMyLocationButtonEnabled = true
            } else {
                map?.isMyLocationEnabled = false
                map?.uiSettings?.isMyLocationButtonEnabled = false
                lastKnownLocation = null
                getLocationPermission()
            }
        } catch (e : SecurityException) {
            Log.e("Exception: %s", e.message, e)
        }
    }

    companion object {
        private val TAG = MapsFragment::class.java.simpleName
        private const val DEFAULT_ZOOM = 15
        private const val PERMISSIONS_REQUEST_ACCESS_FINE_LOCATION = 1

        private const val KEY_CAMERA_POSITION = "camera_position"
        private const val KEY_LOCATION = "location"

        private const val M_MAX_ENTRIES = 5
    }
}