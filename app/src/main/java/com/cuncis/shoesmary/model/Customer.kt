package com.cuncis.shoesmary.model

import com.midtrans.sdk.corekit.core.Constants
import com.midtrans.sdk.corekit.core.LocalDataHandler
import com.midtrans.sdk.corekit.core.TransactionRequest
import com.midtrans.sdk.corekit.models.*
import com.midtrans.sdk.corekit.models.snap.Authentication
import com.midtrans.sdk.corekit.models.snap.CreditCard
import java.util.*
import kotlin.collections.ArrayList


class CustomerModel {
     companion object {

         private fun customerDetails(): CustomerDetails {
             val customerDetails = CustomerDetails()
             customerDetails.firstName = "Karang Reksa Ginolla"
             customerDetails.phone = "081553847135"
             customerDetails.email = "karang.reksa@gmail.com"

             return customerDetails
         }

         fun userDetails(
             nama: String?,
             email: String?,
             nohp: String?,
             address: String?,
             city: String?,
             codepos: String?,
             country: String?
         ) {
             var userDetail: UserDetail?
             userDetail =
                 LocalDataHandler.readObject("user_details", UserDetail::class.java)
             if (userDetail == null) {
                 userDetail = UserDetail()
                 userDetail.userFullName = nama
                 userDetail.email = email
                 userDetail.phoneNumber = nohp
                 userDetail.userId = UUID.randomUUID().toString()
                 val userAddresses: MutableList<UserAddress> = ArrayList()
                 val userAddress = UserAddress()
                 userAddress.address = address
                 userAddress.city = city
                 userAddress.country = country
                 userAddress.zipcode = codepos
                 userAddress.addressType = Constants.ADDRESS_TYPE_BOTH
                 userAddresses.add(userAddress)
                 userDetail.userAddresses = ArrayList(userAddresses)
                 LocalDataHandler.saveObject("user_details", userDetail)
             }
         }

         fun transactionRequest(id: String, price: Double, qty: Int, name: String): TransactionRequest {
             val transactionRequest = TransactionRequest("${System.currentTimeMillis()}", price)
             transactionRequest.customerDetails = customerDetails()

             val details = ItemDetails(id, price, qty, name)

             val itemDetails = ArrayList<ItemDetails>()
             itemDetails.add(details)

             transactionRequest.itemDetails = itemDetails

             val creditCard = CreditCard()
             creditCard.isSaveCard = false
             creditCard.authentication = Authentication.AUTH_RBA
             creditCard.bank = BankType.MANDIRI

             transactionRequest.creditCard = creditCard

             return transactionRequest
         }

     }
}