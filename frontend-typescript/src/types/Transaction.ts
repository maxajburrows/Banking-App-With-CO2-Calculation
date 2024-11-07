import {Account} from "./Account.ts";
import {Category} from "./Category.ts";

export type Transaction = {
    transactionId: number;
    transactionOwner: Account;
    toBankAccount: string;
    transactionType: string;
    amount: number;
    description: string;
    category: Category;
    kgCo2: number;
    date: string;
}
