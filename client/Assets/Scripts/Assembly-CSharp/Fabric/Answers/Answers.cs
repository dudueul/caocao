using System.Collections.Generic;
using Fabric.Answers.Internal;
using UnityEngine;

namespace Fabric.Answers
{
	public class Answers : MonoBehaviour
	{
		private static IAnswers implementation;

		private static IAnswers Implementation
		{
			get
			{
				return null;
			}
		}

		public static void LogSignUp(string method = null, bool? success = null, Dictionary<string, object> customAttributes = null)
		{
		}

		public static void LogLogin(string method = null, bool? success = null, Dictionary<string, object> customAttributes = null)
		{
		}

		public static void LogShare(string method = null, string contentName = null, string contentType = null, string contentId = null, Dictionary<string, object> customAttributes = null)
		{
		}

		public static void LogInvite(string method = null, Dictionary<string, object> customAttributes = null)
		{
		}

		public static void LogLevelStart(string level = null, Dictionary<string, object> customAttributes = null)
		{
		}

		public static void LogLevelEnd(string level = null, double? score = null, bool? success = null, Dictionary<string, object> customAttributes = null)
		{
		}

		public static void LogAddToCart(decimal? itemPrice = null, string currency = null, string itemName = null, string itemType = null, string itemId = null, Dictionary<string, object> customAttributes = null)
		{
		}

		public static void LogPurchase(decimal? price = null, string currency = null, bool? success = null, string itemName = null, string itemType = null, string itemId = null, Dictionary<string, object> customAttributes = null)
		{
		}

		public static void LogStartCheckout(decimal? totalPrice = null, string currency = null, int? itemCount = null, Dictionary<string, object> customAttributes = null)
		{
		}

		public static void LogRating(int? rating = null, string contentName = null, string contentType = null, string contentId = null, Dictionary<string, object> customAttributes = null)
		{
		}

		public static void LogContentView(string contentName = null, string contentType = null, string contentId = null, Dictionary<string, object> customAttributes = null)
		{
		}

		public static void LogSearch(string query = null, Dictionary<string, object> customAttributes = null)
		{
		}

		public static void LogCustom(string eventName, Dictionary<string, object> customAttributes = null)
		{
		}
	}
}
