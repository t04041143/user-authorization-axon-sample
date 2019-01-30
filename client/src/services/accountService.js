import httpClient from "../utils/http-client";

export function userSignUpUserName(username, password) {
  return httpClient.post("/user/sign-up-username", { username, password });
}

export function signInWithPassword(idInAccountType, accountType, password) {
  return httpClient.post("/user/sign-in-with-password", { idInAccountType, accountType, password });
}
