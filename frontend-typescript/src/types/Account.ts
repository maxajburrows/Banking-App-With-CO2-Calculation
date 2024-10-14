import {Transaction} from "./Transaction.ts";

export type Account = {
    // TODO: Add the missing properties
    iban: string;
    accountName: string;
    transactions: Transaction[];
    accountOwner: string;
    balance: number;
}
