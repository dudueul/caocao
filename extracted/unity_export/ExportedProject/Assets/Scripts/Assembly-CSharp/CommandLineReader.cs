using System;
using System.Collections.Generic;

public class CommandLineReader
{
	private const string CUSTOM_ARGS_PREFIX = "-CustomArgs:";

	private const char CUSTOM_ARGS_SEPARATOR = ';';

	public static string[] GetCommandLineArgs()
	{
		return null;
	}

	public static string GetCommandLine()
	{
		return null;
	}

	public static Dictionary<string, string> GetCustomArguments(bool mandatory)
	{
		return null;
	}

	public static string GetCustomArgument(string argumentName, bool mandatory = false)
	{
		return null;
	}

	public static string GetLauncherArg(string key)
	{
		return null;
	}

	public static bool CheckLauncherOption(string key, string opt, StringComparison comparision = StringComparison.OrdinalIgnoreCase)
	{
		return false;
	}
}
