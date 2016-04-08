<?php
	
	include_once("../autoloader.php");

	$api_token = $_POST["api_token"];
	$username = $_POST["username"];
	$password = $_POST["password"];

	if (!ApiToken::isApiTokenValid($api_token))
	{
		die(Response::createErrorResponse(false, "Invalid API Token"));
	}

	if (isset($username, $password))
	{
		if (User::usernameIsTaken($username))
		{
			die(Response::createErrorResponse(true, "Username is already taken!"));
		}
		else
		{
			$newUser = User::registerNewUser($username, $password);

			if ($newUser)
			{
				Debt::createDebtForUser($newUser->getUserId());

				die(Response::createSuccessResponseWithData($newUser->toDictionary()));
			}
			else
			{
				die(Response::createErrorResponse(true, "Error creating user. Please try again later!"));
			}
		}
	}

	die(Response::createErrorResponse(false, "Invalid POST params"));

?>