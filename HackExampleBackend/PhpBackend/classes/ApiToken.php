<?php

	class ApiToken
	{
		public static function isApiTokenValid($api_token)
		{
			if (isset($api_token))
			{
				$db = Database::getInstance();
				$mysqli = $db->getConnection();

				$sql_query = "SELECT token_id FROM api_tokens WHERE token = '$api_token'";
				$result = $mysqli->query($sql_query);

				if ($result && $result->num_rows > 0)
				{
					return true;
				}
			}

			return false;
		}
	}

?>