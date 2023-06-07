// Auth
  // POST
  export const REGISTER_USER = '/auth/register'
  export const LOGIN_ENDPOINT = '/auth/login'
  export const LOGOUT_ENDPOINT = '/auth/logout'


// Account
  // GET
  export const GET_ACCOUNT_BY_USER_ID = '/account/byUserId?userId='


// Loans
  // GET
  export const GET_USER_LOANS_ENDPOINT = '/loans/user?userId=';

  // POST
  export const SEND_REQUEST_FOR_LOAN_ENDPOINT = '/loans/loanRequest';

  
// ANNOUNCEMENTS
  // GET
  export const RANDOM_ANNOUNCEMENT_ENDPOINT = '/announcement/current/random'
  export const ALL_CURRENT_ANNOUNCEMENT_ENDPOINT = '/announcement/current/all'
  export const ADD_ANNOUNCEMENT_ENDPOINT = '/announcement/add'
  export const GET_AVAILABLE_ANNOUNCEMENTS = '/announcement/available'

  // POST
  export const DELETE_ANNOUNCEMENT = '/announcement/delete'


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