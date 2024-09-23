import {Account} from "./Account.ts";

export type Transaction = {
    transactionId: number;
    transactionOwner: Account;
    toBankAccount: string;
    transactionType: string;
    amount: number;
    description: string;
    category: string;
    date: string;
}
