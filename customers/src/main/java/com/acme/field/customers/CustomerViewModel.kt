package com.acme.field.customers;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.List;

public class CustomerViewModel extends ViewModel {

    private final CustomerRepository repository;
    private final MutableLiveData<List<Customer>> customers = new MutableLiveData<>();
    private final MutableLiveData<String> error = new MutableLiveData<>();
    private String query = "";

    public CustomerViewModel(CustomerRepository repository) {
        this.repository = repository;
        refresh();
    }

    public LiveData<List<Customer>> getCustomers() {
        return customers;
    }

    public LiveData<String> getError() {
        return error;
    }

    public void setQuery(String query) {
        this.query = query;
        refresh();
    }

    public void refresh() {
        List<Customer> found = CustomerFilter.search(repository.all(), query);
        CustomerFilter.sortByName(found);
        customers.setValue(found);
    }

    public void load(String id) {
        repository.findById(id, new ResultCallback<Customer>() {
            @Override
            public void onSuccess(Customer result) {
                refresh();
            }

            @Override
            public void onError(Throwable throwable) {
                error.postValue(throwable.getMessage());
            }
        });
    }
}
