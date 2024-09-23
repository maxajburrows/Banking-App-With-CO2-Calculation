import {Transaction} from "./Transaction.ts";

export type Account = {
    // TODO: Add the missing properties
    iban: string;
    name: string;
    transactions: Transaction[];
    accountOwner: string;
}
