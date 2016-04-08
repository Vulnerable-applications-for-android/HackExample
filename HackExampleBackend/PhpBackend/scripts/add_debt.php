<?php
	
	include_once("../autoloader.php");

	$api_token = $_POST["api_token"];
	$user_id = $_POST["user_id"];
	$login_token = $_POST["login_token"];
	$additional_debt = $_POST["additional_debt"];

	if (!ApiToken::isApiTokenValid($api_token))
	{
		die(Response::createErrorResponse(false, "Invalid API Token"));
	}

	if (isset($user_id, $login_token, $additional_debt))
	{
		if (User::loginTokenIsValid($user_id, $login_token))
		{
			$success = Debt::addDebtToUser($user_id, $additional_debt);

			if ($success)
			{
				die(Response::createSuccessResponse(true, "Success!"));
			}
			else
			{
				die(Response::createErrorResponse(true, "Failed to buy the thing!"));
			}
		}
		else
		{
			die(Response::createErrorResponse(false, "Invalid user_id or login token"));
		}
	}

	die(Response::createErrorResponse(false, "Invalid POST params"));

?>