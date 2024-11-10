export type NewTransaction = {
    transactionOwnerIban: string;
    toBankAccount: string;
    amount: number;
    description: string;
}
