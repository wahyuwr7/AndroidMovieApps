package com.wrseven.movieapps.utils

import androidx.sqlite.db.SimpleSQLiteQuery

object SortUtils {
    const val DEFAULTS = "Default"
    const val RATING = "Rating"
    const val NEWEST = "Newest"
    const val OLDEST = "Oldest"

    fun getSortedQuery(filter: String, table_name: String): SimpleSQLiteQuery {
        val simpleQuery = StringBuilder().append("SELECT * FROM $table_name ")
        when (filter) {
            DEFAULTS -> simpleQuery.append("")
            RATING -> simpleQuery.append("ORDER BY rating DESC")
            NEWEST -> simpleQuery.append("ORDER BY year DESC")
            OLDEST -> simpleQuery.append("ORDER BY year ASC")
        }
        return SimpleSQLiteQuery(simpleQuery.toString())
    }
}