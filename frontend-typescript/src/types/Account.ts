import {Transaction} from "./Transaction.ts";

export type Account = {
    iban: string;
    name: string;
    transactions: Transaction[];
    accountOwner: string; // Do I need to define a type for this?
}
