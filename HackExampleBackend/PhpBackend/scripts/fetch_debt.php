<?php
	
	include_once("../autoloader.php");

	$api_token = $_POST["api_token"];
	$user_id = $_POST["user_id"];
	$login_token = $_POST["login_token"];

	if (!ApiToken::isApiTokenValid($api_token))
	{
		die(Response::createErrorResponse(false, "Invalid API Token"));
	}

	if (isset($user_id, $login_token))
	{
		if (User::loginTokenIsValid($user_id, $login_token))
		{
			$debt = Debt::fetchDebtForUser($user_id);

			if ($debt)
			{
				die(Response::createSuccessResponseWithData($debt->toDictionary()));
			}
			else
			{
				die(Response::createErrorResponse(true, "Failed to find debt!"));
			}
		}
		else
		{
			die(Response::createErrorResponse(false, "Invalid user_id or login token"));
		}
	}

	die(Response::createErrorResponse(false, "Invalid POST params"));

?>