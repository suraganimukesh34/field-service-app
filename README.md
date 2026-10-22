# field-service-app (Java)

An Android app for field technicians, written in Java and not yet migrated to Kotlin. It is used to test the
Java-to-Kotlin migration pipeline: each module is handed to the migration agent, which converts it and opens
a pull request.

| Module | What it contains |
|---|---|
| `customers` | Customer model, repository with a cache and callbacks, search filter, formatter, ViewModel with LiveData |
| `scheduling` | Appointments with a builder, a status enum with allowed transitions, a service with listeners, time helpers |
| `parts` | Spare parts catalogue, categories, stock listeners, a singleton repository, a map-to-model mapper |
| `invoicing` | Invoice and line items, calculator, Retrofit API, RecyclerView adapter, an activity |

Layout and resource files are not included: this repository exists to be read and converted, not built.
