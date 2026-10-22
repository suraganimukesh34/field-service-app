package com.acme.field.customers

object CustomerFormatter {

    const val UNKNOWN = "Unknown customer"
    private const val MAX_NAME_LENGTH = 28

    @JvmStatic
    fun displayName(customer: Customer?): String {
        val name = customer?.name?.trim() ?: return UNKNOWN
        return if (name.length > MAX_NAME_LENGTH) name.substring(0, MAX_NAME_LENGTH - 1) + "…" else name
    }

    @JvmStatic
    fun contactLine(customer: Customer): String {
        val line = StringBuilder()
        line.append(customer.email)
        customer.phone?.let { line.append(" | ").append(it) }
        return line.toString()
    }

    @JvmStatic
    fun statusLabel(customer: Customer): String = if (customer.isActive) "Active" else "Inactive"
}
