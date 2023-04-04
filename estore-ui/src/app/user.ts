export interface User {
    username: string;
    //password: string;
    first_name: string;
    last_name: string;
    cart_number: number;
    is_admin: boolean;
}
export interface UserHandler {
    success: boolean[];
    username: string[];
    //password: string[];
    first_name: string[];
    last_name: string[];
    cart_number: number[];
    is_admin: boolean[];
}