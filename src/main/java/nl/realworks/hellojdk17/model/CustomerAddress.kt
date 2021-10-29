package nl.realworks.hellojdk17.model

import org.hibernate.annotations.GenericGenerator
import javax.persistence.*

@Entity
data class CustomerAddress @JvmOverloads constructor(
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    @Column(name = "address_id", nullable = false)
    @Id
    val id: String? = "",
    val city: String?,
    val cityRegion: CityRegion?,
    val postcode: String?,
    val streetName: String?,
    val houseNumber: Int?,
    val additional: String?,
    val country: String?,
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "customer_id", nullable = false)
    val customer: Customer,
    val addressText: String?
) {


    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as CustomerAddress

        if (id != null && id != other.id) return false
        if (city != other.city) return false
        if (cityRegion != other.cityRegion) return false
        if (postcode != other.postcode) return false
        if (streetName != other.streetName) return false
        if (houseNumber != other.houseNumber) return false
        if (additional != other.additional) return false
        if (country != other.country) return false
        if (customer != other.customer) return false
        if (addressText != other.addressText) return false

        return true
    }

    override fun hashCode(): Int {
        var result = id?.hashCode() ?: 0
        result = 31 * result + (city?.hashCode() ?: 0)
        result = 31 * result + (cityRegion?.hashCode() ?: 0)
        result = 31 * result + (postcode?.hashCode() ?: 0)
        result = 31 * result + (streetName?.hashCode() ?: 0)
        result = 31 * result + (houseNumber ?: 0)
        result = 31 * result + (additional?.hashCode() ?: 0)
        result = 31 * result + (country?.hashCode() ?: 0)
        result = 31 * result + (addressText?.hashCode() ?: 0)
        result = 31 * result + (customer.id.hashCode())
        return result
    }
}