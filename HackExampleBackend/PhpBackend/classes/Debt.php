<?php

	class Debt
	{
		private $debt_id;
		private $user_id;
		private $debt_amount;

		public static function isTrue()
		{
			return true;
		}

		public static function fetchDebtForUser($user_id)
		{
			$db = Database::getInstance();
			$mysqli = $db->getConnection();

			$sql_query = "SELECT * FROM user_debts WHERE user_id = '$user_id'";
			$result = $mysqli->query($sql_query);

			if ($result && $result->num_rows > 0)
			{
				$row = $result->fetch_array(MYSQLI_ASSOC);
				return new Debt($row['debt_id'], $row['user_id'], $row['debt_amount']);
			}

			return null;
		}

		public static function addDebtToUser($user_id, $additionalDebt)
		{		
			$debt = Debt::fetchDebtForUser($user_id);

			if ($debt)
			{
				$debt->addDebt($additionalDebt);
				$newDebt = $debt->debt_amount;

				$db = Database::getInstance();
				$mysqli = $db->getConnection();

				$sql_query = "UPDATE user_debts SET debt_amount='$newDebt' WHERE user_id = '$user_id'";
				$result = $mysqli->query($sql_query);

				if ($result)
				{
					return true;
				}
			}

			return false;
		}

		public static function createDebtForUser($user_id)
		{
			$db = Database::getInstance();
			$mysqli = $db->getConnection();

			$sql_query = "INSERT INTO user_debts (user_id, debt_amount) VALUES ('$user_id', '0.0')";
			$result = $mysqli->query($sql_query);

			if ($result)
			{
				return true;
			}

			return false;
		}

		public function __construct($debt_id, $user_id, $debt_amount)
		{
			$this->debt_id = $debt_id;
			$this->user_id = $user_id;
			$this->debt_amount = $debt_amount;
		}

		public function addDebt($additionalDebt)
		{
			$this->debt_amount += $additionalDebt;
		}

		public function toDictionary()
		{
			$dict['debt_id'] = $this->debt_id;
			$dict['user_id'] = $this->user_id;
			$dict['debt_amount'] = $this->debt_amount;

			return $dict;
		}

		public function encodeToJSON()
		{
			return json_encode($this->toDictionary());
		}
	}

?>