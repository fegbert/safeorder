package com.groupthree.safeorder.database

import androidx.annotation.WorkerThread

class ProductRepository(private val productDAO: ProductDAO) {

    val allProducts : List<Product> = productDAO.getAllProducts()

    @WorkerThread
    suspend fun getProduct(productID : Int) = productDAO.getProduct(productID)

    @WorkerThread
    suspend fun insertProduct(product: Product) = productDAO.insertProduct(product)

    @WorkerThread
    suspend fun clearAll() = productDAO.clearAll()
}