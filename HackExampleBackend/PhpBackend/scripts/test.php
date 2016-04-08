<?php
	
	include_once("../autoloader.php");

	$user_id = 2;
	$additionalDebt = 10.0;

	var_dump(Debt::createDebtForUser($user_id));
?>