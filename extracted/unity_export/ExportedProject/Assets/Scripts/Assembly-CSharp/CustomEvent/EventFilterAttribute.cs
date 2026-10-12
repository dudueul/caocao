using System;

namespace CustomEvent
{
	public class EventFilterAttribute : Attribute
	{
		public string Name { get; private set; }

		public EventFilterAttribute(string name)
		{
		}
	}
}
