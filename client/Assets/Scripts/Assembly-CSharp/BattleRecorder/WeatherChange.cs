using Caocao;

namespace BattleRecorder
{
	public sealed class WeatherChange : Action
	{
		private Caocao.Weather m_prevWeather;

		private Caocao.Weather m_newWeather;

		public override byte key
		{
			get
			{
				return 0;
			}
		}

		public WeatherChange(Caocao.Weather prevWeather, Caocao.Weather newWeather)
		{
		}

		public override void _Apply()
		{
		}

		public override void _Revert()
		{
		}

		public override bool NeedToRecord()
		{
			return false;
		}

		public static WeatherChange Read(OptimizedBinaryReader r)
		{
			return null;
		}

		public override void Write(OptimizedBinaryWriter w)
		{
		}
	}
}
