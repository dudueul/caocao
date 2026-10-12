using System;

namespace MAssist
{
	public abstract class MInstanceMemberEditItem
	{
		public enum ItemType
		{
			INSTANCE = 0,
			MEMBER_INFO = 1
		}

		public ItemType itemType;

		protected object _currentInstance;

		protected string _currentValueStr;

		protected string _changeValueStr;

		protected object _changeValue;

		protected bool _isChangeable;

		public Action CloseAction;

		public abstract bool convertValue(string valueStr, out object value);

		public abstract void OnGUI();
	}
}
