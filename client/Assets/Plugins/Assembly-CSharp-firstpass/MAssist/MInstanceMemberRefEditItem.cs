using System;

namespace MAssist
{
	public class MInstanceMemberRefEditItem<T> : MInstanceMemberEditItem
	{
		private Func<T> _get;

		private Action<T> _set;

		private Type _genericType;

		private string _genericTypeStr;

		private string _name;

		private bool _isMissing;

		public T Value
		{
			get
			{
				return default(T);
			}
			set
			{
			}
		}

		public MInstanceMemberRefEditItem(Func<T> get, Action<T> set, string name = "")
		{
		}

		public override bool convertValue(string valueStr, out object value)
		{
			value = null;
			return false;
		}

		public override void OnGUI()
		{
		}
	}
}
