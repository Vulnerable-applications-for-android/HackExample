<?php

	class User
	{
		private $user_id;
		private $username;
		private $login_token;

		public static function loginTokenIsValid($user_id, $login_token)
		{
			$db = Database::getInstance();
			$mysqli = $db->getConnection();

			$sql_query = "SELECT user_id FROM user_credentials WHERE user_id = '$user_id' AND login_token = '$login_token'";
			$result = $mysqli->query($sql_query);

			if ($result && $result->num_rows > 0)
			{
				return true;
			}

			return false;
		}

		public static function usernameIsTaken($username)
		{
			$db = Database::getInstance();
			$mysqli = $db->getConnection();

			$sql_query = "SELECT user_id FROM user_credentials WHERE username = '$username'";
			$result = $mysqli->query($sql_query);

			if ($result && $result->num_rows > 0)
			{
				return true;
			}

			return false;
		}

		public static function registerNewUser($username, $hashedPassword)
		{		
			$login_token = User::generatelogin_token($username);

			$db = Database::getInstance();
			$mysqli = $db->getConnection();

			$sql_query = "INSERT INTO user_credentials (username, password, login_token) VALUES ('$username', '$hashedPassword', '$login_token')";
			$result = $mysqli->query($sql_query);

			if ($result)
			{
				$user_id = $mysqli->insert_id;
				return new User($user_id, $username, $login_token);
			}

			return null;
		}

		public static function login($username, $hashedPassword)
		{
			$db = Database::getInstance();
			$mysqli = $db->getConnection();

			$sql_query = "SELECT * FROM user_credentials WHERE username = '$username' AND password = '$hashedPassword'";
			$result = $mysqli->query($sql_query);

			if ($result && $result->num_rows > 0)
			{
				$row = $result->fetch_array(MYSQLI_ASSOC);
				return new User($row['user_id'], $row['username'], $row['login_token']);
			}

			return null;
		}

		public static function generatelogin_token($username)
		{
			return md5($username . time());
		}

		public function __construct($user_id, $username, $login_token)
		{
			$this->user_id = $user_id;
			$this->username = $username;
			$this->login_token = $login_token;
		}

		public function getUserId()
		{
			return $this->user_id;
		}

		public function toDictionary()
		{
			$dict['user_id'] = $this->user_id;
			$dict['username'] = $this->username;
			$dict['login_token'] = $this->login_token;

			return $dict;
		}

		public function encodeToJSON()
		{
			return json_encode($this->toDictionary());
		}
	}

?>