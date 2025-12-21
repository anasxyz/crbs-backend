// AutoConfirmUserOnCreation
// Lambda function for auto confirming and auto verifying user on Cognito user sign up event
// Runs as a pre - sign up lambda trigger on Cognito

export const handler = async (event) => {
  event.response.autoConfirmUser = true;

  if (event.request.userAttributes.hasOwnProperty("email")) {
    event.response.autoVerifyEmail = true;
  }

  return event;
};
