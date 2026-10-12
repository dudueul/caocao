using System.Collections.Generic;
using System.IO;

namespace LitJson
{
	public class JsonReader
	{
		private static IDictionary<int, IDictionary<int, int[]>> parse_table;

		private Stack<int> automaton_stack;

		private int current_input;

		private int current_symbol;

		private bool end_of_json;

		private bool end_of_input;

		private Lexer lexer;

		private bool parser_in_string;

		private bool parser_return;

		private bool read_started;

		private TextReader reader;

		private bool reader_is_owned;

		private bool skip_non_members;

		private object token_value;

		private JsonToken token;

		public bool AllowComments
		{
			get
			{
				return false;
			}
			set
			{
			}
		}

		public bool AllowSingleQuotedStrings
		{
			get
			{
				return false;
			}
			set
			{
			}
		}

		public bool SkipNonMembers
		{
			get
			{
				return false;
			}
			set
			{
			}
		}

		public bool EndOfInput
		{
			get
			{
				return false;
			}
		}

		public bool EndOfJson
		{
			get
			{
				return false;
			}
		}

		public JsonToken Token
		{
			get
			{
				return JsonToken.None;
			}
		}

		public object Value
		{
			get
			{
				return null;
			}
		}

		static JsonReader()
		{
		}

		public JsonReader(string json_text)
		{
		}

		public JsonReader(TextReader reader)
		{
		}

		private JsonReader(TextReader reader, bool owned)
		{
		}

		private static void PopulateParseTable()
		{
		}

		private static void TableAddCol(ParserToken row, int col, params int[] symbols)
		{
		}

		private static void TableAddRow(ParserToken rule)
		{
		}

		private void ProcessNumber(string number)
		{
		}

		private void ProcessSymbol()
		{
		}

		private bool ReadToken()
		{
			return false;
		}

		public void Close()
		{
		}

		public bool Read()
		{
			return false;
		}
	}
}
