namespace WebSocketSharp.Net
{
	internal class HttpHeaderInfo
	{
		private HttpHeaderType _type;

		internal bool IsMultiValueInRequest
		{
			get
			{
				return false;
			}
		}

		internal bool IsMultiValueInResponse
		{
			get
			{
				return false;
			}
		}

		public bool IsRequest
		{
			get
			{
				return false;
			}
		}

		public bool IsResponse
		{
			get
			{
				return false;
			}
		}

		public string Name { get; set; }

		public HttpHeaderType Type
		{
			get
			{
				return HttpHeaderType.Unspecified;
			}
			set
			{
			}
		}

		public bool IsMultiValue(bool response)
		{
			return false;
		}

		public bool IsRestricted(bool response)
		{
			return false;
		}
	}
}
