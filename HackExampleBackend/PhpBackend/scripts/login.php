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
		$user = User::login($username, $password);

		if ($user)
		{
			die(Response::createSuccessResponseWithData($user->toDictionary()));
		}
		else
		{
			die(Response::createErrorResponse(true, "Username or password was invalid!"));
		}
	}

	die(Response::createErrorResponse(false, "Invalid POST params"));

?>