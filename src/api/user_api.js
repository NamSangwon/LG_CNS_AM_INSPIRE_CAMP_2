const API_BASE_URL = "http://localhost:8080/api/user"

export const createUser = async (json) => {
    const url = API_BASE_URL + "/signup";
    const res = await fetch(url, {
        headers : {
            "Content-Type" : "application/json"
        },
        method : "POST",
        body : json
    });

    const data = await res.json();

    return data;
}