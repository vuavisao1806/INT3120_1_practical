package com.example.marsphotos.fake

import com.example.unit5_pathway2_bookshelf.data.BookShelfRepository
import com.example.unit5_pathway2_bookshelf.network.Volume

class FakeNetworkBookShelfRepository : BookShelfRepository {
    override suspend fun getBookShelf(): List<Volume> {
        val volumeList = mutableListOf<Volume>()
        volumeList.addAll(FakeDataSource.volumeList)
        volumeList.addAll(FakeDataSource.volumeList)
        return volumeList.toList()
    }
}