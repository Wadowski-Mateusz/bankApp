export interface LoanDTO {
  id: string,
  name: string;
  dateFrom: Date;
  dateTo: Date;
  interest: number;
  amount: number;
  due: number;
}

export interface LoanRequestDTO {
  userId: string;
  name: string;
  dateFrom: Date;
  amount: number;
  months: number;
  interest: number;
}