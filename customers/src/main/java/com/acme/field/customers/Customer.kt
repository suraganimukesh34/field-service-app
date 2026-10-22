package com.acme.field.customers

// Not a data class: equals/hashCode intentionally ignore phone and address, and the class is mutable.
class Customer(
    val id: String,
    var name: String?,
    var email: String?,
    var phone: String?,
    var address: String?,
    var isActive: Boolean,
) {

    constructor(id: String, name: String?, email: String?) : this(id, name, email, null, null, true)

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other == null || javaClass != other.javaClass) return false
        other as Customer
        return isActive == other.isActive &&
            id == other.id &&
            name == other.name &&
            email == other.email
    }

    override fun hashCode(): Int = java.util.Objects.hash(id, name, email, isActive)

    override fun toString(): String =
        "Customer{id='$id', name='$name', email='$email', active=$isActive}"
}
