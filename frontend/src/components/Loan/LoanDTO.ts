export interface LoanDTO {
  id: string,
  name: string;
  dateFrom: Date;
  dateTo: Date;
  interest: number;
  amount: number;
  due: number;
}