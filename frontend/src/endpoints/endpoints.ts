// Auth
  // POST
  export const REGISTER_USER = '/auth/register'
  export const LOGIN_ENDPOINT = '/auth/login'
  export const LOGOUT_ENDPOINT = '/auth/logout'

// Account
  // GET
  export const GET_ACCOUNT_BY_USER_ID = '/account/byUserId?userId='

// Panel
  // GET
  export const GET_USER_TO_VERIFY_DATA = '/user/verify/data'
  export const GET_USER_TO_VERIFY_SCAN = '/user/verify/scan?userId='

  //  PUT
  export const VERIFY_USER = '/user/verify/verify'

// Transactions
  // GET
  export const GET_ALL_TRANSFERS_OF_USER = '/transaction/user/all?userId=';

  // POST
  export const ADD_TRANSACTION = '/transaction/add';  