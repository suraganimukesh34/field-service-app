package com.acme.field.customers

object CustomerFilter {

    @JvmStatic
    fun search(customers: List<Customer>, query: String?): MutableList<Customer> {
        val needle = query?.trim()?.lowercase() ?: ""
        return customers.filterTo(mutableListOf()) { customer ->
            needle.isEmpty() ||
                customer.name!!.lowercase().contains(needle) ||
                customer.email?.lowercase()?.contains(needle) == true
        }
    }

    @JvmStatic
    fun activeOnly(customers: List<Customer>): MutableList<Customer> =
        customers.filterTo(mutableListOf()) { it.isActive }

    @JvmStatic
    fun sortByName(customers: MutableList<Customer>) {
        customers.sortWith { a, b -> a.name!!.compareTo(b.name!!, ignoreCase = true) }
    }
}
