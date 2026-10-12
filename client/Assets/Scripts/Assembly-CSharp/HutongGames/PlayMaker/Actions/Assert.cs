using PlayMaker.ConditionalExpression;

namespace HutongGames.PlayMaker.Actions
{
	public class Assert : FsmStateAction, IEvaluatorContext
	{
		public enum AssertType
		{
			IsTrue = 0,
			IsFalse = 1
		}

		public FsmString expression;

		public AssertType assert;

		public bool everyFrame;

		private string cachedExpression;

		public CompiledAst Ast { get; set; }

		public string LastErrorMessage { get; set; }

		FsmVar IEvaluatorContext.GetVariable(string name)
		{
			return null;
		}
	}
}
