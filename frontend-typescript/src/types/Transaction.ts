import {Account} from "./Account.ts";

export type Transaction = {
    transactionId: number;
    fromBankAccount: Account;
    toBankAccount: Account;
    amount: number;
    description: string;
    category: string;
    date: string;
}
