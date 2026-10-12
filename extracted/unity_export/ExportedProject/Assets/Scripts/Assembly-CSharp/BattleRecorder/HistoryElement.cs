using System.Text;

namespace BattleRecorder
{
	public abstract class HistoryElement
	{
		protected static StringBuilder ms_sb;

		private static int ms_sequence;

		public readonly int sequence;

		public virtual void _Apply()
		{
		}

		public virtual void _Revert()
		{
		}

		public virtual bool ApplyOnLoading()
		{
			return false;
		}

		protected virtual bool _ApplyOnLoading()
		{
			return false;
		}

		public virtual void Redo()
		{
		}
	}
}
