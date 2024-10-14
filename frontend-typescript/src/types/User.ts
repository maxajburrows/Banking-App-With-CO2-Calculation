import {Account} from "./Account.ts";

export type User = {
    userName: string;
    firstName: string;
    lastName: string;
    password: string;
    bankAccounts: Account[];
}
