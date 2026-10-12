using System.Collections.Generic;
using UnityEngine;

namespace Fabric.Answers.Internal
{
	internal class AnswersEventInstanceJavaObject
	{
		public AndroidJavaObject javaObject;

		public AnswersEventInstanceJavaObject(string eventType, Dictionary<string, object> customAttributes, params string[] args)
		{
		}

		public void PutMethod(string method)
		{
		}

		public void PutSuccess(bool? success)
		{
		}

		public void PutContentName(string contentName)
		{
		}

		public void PutContentType(string contentType)
		{
		}

		public void PutContentId(string contentId)
		{
		}

		public void PutCurrency(string currency)
		{
		}

		public void InvokeSafelyAsCurrency(string methodName, string currency)
		{
		}

		public void InvokeSafelyAsBoolean(string methodName, bool? arg)
		{
		}

		public void InvokeSafelyAsInt(string methodName, int? arg)
		{
		}

		public void InvokeSafelyAsString(string methodName, string arg)
		{
		}

		public void InvokeSafelyAsDecimal(string methodName, object arg)
		{
		}

		public void InvokeSafelyAsDouble(string methodName, object arg)
		{
		}

		private static bool IsNumericType(object o)
		{
			return false;
		}

		private static AndroidJavaObject AsDouble(object param)
		{
			return null;
		}
	}
}
