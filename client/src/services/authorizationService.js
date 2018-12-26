import wsClient from "../utils/websocketInstance";
import { TYPE as USER_IDENTITY_TYPE } from "../constants/accountConstants";

export function subscribeEvent(callback) {
  wsClient.subscribe(`/user/topic/authorization-event`, callback);
}

export function signUpWithPassword(userId, accountId, password) {
  wsClient.send("/app/user-authorization/sign-up-with-password", {}, JSON.stringify({
    senderId: userId,
    accountId: {
      id: accountId,
      type: USER_IDENTITY_TYPE.ACCOUNT
    },
    password: password
  }));
}

export function signInWithPassword(userId, accountId, password) {
  wsClient.send("/app/user-authorization/sign-in-with-password", {}, JSON.stringify({
    senderId: userId,
    accountId: {
      id: accountId,
      type: USER_IDENTITY_TYPE.ACCOUNT
    },
    password: password
  }));
}
