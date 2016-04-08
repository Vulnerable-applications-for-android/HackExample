<?php

	class Response
	{
		private $isError;
		private $shouldDisplayMessage;
		private $message;
		private $data;

		public static function createErrorResponse($shouldDisplayMessage, $message)
		{
			$response = new Response(true, $shouldDisplayMessage, $message, null);
			return $response->encodeToJSON();
		}

		public static function createSuccessResponse($shouldDisplayMessage, $message)
		{
			$response = new Response(false, $shouldDisplayMessage, $message, null);
			return $response->encodeToJSON();
		}

		public static function createSuccessResponseWithData($data)
		{
			$response = new Response(false, false, null, $data);
			return $response->encodeToJSON();
		}

		private function __construct($isError, $shouldDisplayMessage, $message, $data)
		{
			$this->isError = $isError;
			$this->shouldDisplayMessage = $shouldDisplayMessage;
			$this->message = $message;
			$this->data = $data;
		}

		public function toDictionary()
		{
			$dict['isError'] = $this->isError;
			$dict['shouldDisplayMessage'] = $this->shouldDisplayMessage;

			if ($this->message)
			{
				$dict['message'] = $this->message;
			}

			if ($this->data)
			{
				$dict['data'] = $this->data;
			}

			return $dict;
		}

		public function encodeToJSON()
		{
			return json_encode($this->toDictionary());
		}
	}

?>