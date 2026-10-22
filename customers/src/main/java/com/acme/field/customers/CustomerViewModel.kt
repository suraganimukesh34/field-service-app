package com.acme.field.customers

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class CustomerViewModel(private val repository: CustomerRepository) : ViewModel() {

    private val _customers = MutableLiveData<List<Customer>>()
    private val _error = MutableLiveData<String?>()
    private var query: String? = ""

    val customers: LiveData<List<Customer>> get() = _customers
    val error: LiveData<String?> get() = _error

    init {
        refresh()
    }

    // Explicit function (not a property setter) so Java keeps setQuery(String) with no new getQuery().
    fun setQuery(query: String?) {
        this.query = query
        refresh()
    }

    fun refresh() {
        val found = CustomerFilter.search(repository.all(), query)
        CustomerFilter.sortByName(found)
        _customers.value = found
    }

    fun load(id: String) {
        repository.findById(id, object : ResultCallback<Customer> {
            override fun onSuccess(result: Customer) {
                refresh()
            }

            override fun onError(error: Throwable) {
                _error.postValue(error.message)
            }
        })
    }
}
