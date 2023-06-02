export interface TransactionAddDTO {
  id: string | null;
  receiverAccountNumber: string;
  senderAccountNumber: string;
  title: string;
  amount: number;
  timestamp: Date;
}

export interface TransactionViewDTO {
  id: string,
  fullName: string,
  title: string,
  amount: number,
  timestamp: Date,
  type: string,
}