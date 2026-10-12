using System;
using System.Collections.Generic;
using CodeStage.AntiCheat.ObscuredTypes;
using LitJson;

public class TransmittableUtil
{
	public delegate bool IsIgnore(JsonData jsonData);

	public static void DeserializeGuild<T>(JsonData jsonData, string keyColName, Dictionary<ulong, T> container, List<ulong> keys) where T : ITransmittable, new()
	{
	}

	public static void DeserializeGuild<T>(JsonData jsonData, string keyColName, Dictionary<int, T> container, List<int> keys) where T : ITransmittable, new()
	{
	}

	public static bool DeserializeUpserted<T>(JsonData jsonData, string keyColName, Dictionary<ulong, T> container, List<ulong> keys, List<T> upsertedList = null, IsIgnore IsIgnoreFunc = null) where T : ITransmittable, new()
	{
		return false;
	}

	public static bool DeserializeDeleted<T>(JsonData jsonData, string keyColName, Dictionary<ulong, T> container, List<ulong> keys, List<T> removedList = null) where T : ITransmittable, new()
	{
		return false;
	}

	public static bool DeserializeUpserted<T>(JsonData jsonData, string keyColName, Dictionary<int, T> container, List<int> keys, List<T> upsertedList = null, IsIgnore IsIgnoreFunc = null) where T : ITransmittable, new()
	{
		return false;
	}

	public static bool DeserializeDeleted<T>(JsonData jsonData, string keyColName, Dictionary<int, T> container, List<int> keys, List<T> removedList = null) where T : ITransmittable, new()
	{
		return false;
	}

	public static bool DeserializeUpserted<T>(JsonData jsonData, string keyColName, Dictionary<ObscuredInt, T> container, List<ObscuredInt> keys, List<T> upsertedList = null, IsIgnore IsIgnoreFunc = null) where T : ITransmittable, new()
	{
		return false;
	}

	public static bool DeserializeDeleted<T>(JsonData jsonData, string keyColName, Dictionary<ObscuredInt, T> container, List<ObscuredInt> keys, List<T> removedList = null) where T : ITransmittable, new()
	{
		return false;
	}

	public static bool DeserializeUpserted<K, T>(JsonData jsonData, Func<JsonData, K> keySelector, Dictionary<K, T> container, List<T> upsertedList = null) where T : ITransmittable, new()
	{
		return false;
	}
}
