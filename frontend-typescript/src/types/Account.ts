import {Transaction} from "./Transaction.ts";

export type Account = {
    iban: string;
    accountName: string;
    transactions: Transaction[];
    accountOwner: string;
    balance: number;
}
