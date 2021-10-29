package nl.realworks.hellojdk17.model

import org.hibernate.annotations.GenericGenerator
import javax.persistence.*

@Entity
data class Customer @JvmOverloads constructor(
    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    @Column(name = "customer_id", nullable = false)
    val id: String? = "",
    val name: String?,
    val lastName: String?,
    val birthday: Int?,
    @OneToMany(mappedBy = "customer", fetch = FetchType.EAGER, cascade = [CascadeType.ALL])
    val customerAddressSet: Set<CustomerAddress> = HashSet()
) {


    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as Customer

        if (id != other.id) return false
        if (name != other.name) return false
        if (lastName != other.lastName) return false
        if (birthday != other.birthday) return false
        if (customerAddressSet != other.customerAddressSet) return false

        return true
    }

    override fun hashCode(): Int {
        var result = id?.hashCode() ?: 0
        result = 31 * result + (name?.hashCode() ?: 0)
        result = 31 * result + (lastName?.hashCode() ?: 0)
        result = 31 * result + (birthday ?: 0)
        result = 31 * result + (customerAddressSet.hashCode())
        return result
    }

}