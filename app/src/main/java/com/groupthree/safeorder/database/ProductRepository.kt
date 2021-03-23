package com.groupthree.safeorder.database

import androidx.annotation.WorkerThread

class ProductRepository(private val productDAO: ProductDAO) {

    val allProducts : List<Product> = productDAO.getAllProducts()

    fun getProduct(productID : Int) = productDAO.getProduct(productID)

    @WorkerThread
    fun insert(product: Product) = productDAO.insertProduct(product)
}