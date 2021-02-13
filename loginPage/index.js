class Auth {

    static login(phoneNumber, password) {
        //if found return true
    
         //making api call

        const userAction = async () => {
            const response = await fetch(`localhost:8081/login?userPhone:${phoneNumber}/userPassword${password}`);
            const userData = await response.json();
            console.log(userData);
    }

        return true;
    }
    
    static signup(phone, password) {
        //if already exist return false
        //else return true
    }

}


const signInButton = $("#signInButton");
const createAccountButton = $("#createAccountButton");

signInButton.click(() => {
    const phoneNumber = $("#inputPhone").val();
    const password = $("#inputPassword").val().toString();
    console.log(phoneNumber, password);
    const status = Auth.login(phoneNumber, password);

});