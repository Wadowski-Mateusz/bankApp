import {AddressDTO} from "./AddressDTO"

export interface UserVerificationDTO {
  userId: string,
  fullName: string,
  email: string,
  birthday: string,
  addressDTO: AddressDTO,
  idNumber: string
}

export interface UserVerifyOrBanDTO {
  userId: string,
  verified: boolean
}