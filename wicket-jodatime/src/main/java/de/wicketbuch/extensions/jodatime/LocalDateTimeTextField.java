package de.wicketbuch.extensions.jodatime;

import java.util.Locale;

import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.model.IModel;
import org.apache.wicket.util.convert.ConversionException;
import org.apache.wicket.util.convert.IConverter;
import org.joda.time.LocalDateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

public class LocalDateTimeTextField extends TextField<LocalDateTime>
{
	private DateTimeFormatter formatter;
	private final IConverter<LocalDateTime> converter = new IConverter<LocalDateTime>()
	{
		@Override
		public LocalDateTime convertToObject(String value, Locale locale) throws ConversionException
		{
			formatter = formatter.withLocale(locale);
			return formatter.parseLocalDateTime(value);
		}

		@Override
		public String convertToString(LocalDateTime value, Locale locale)
		{
			formatter = formatter.withLocale(locale);
			return formatter.print(value);
		}
	};

	public LocalDateTimeTextField(String id)
	{
		this(id, (DateTimeFormatter) null);
	}

	public LocalDateTimeTextField(String id, DateTimeFormatter format)
	{
		this(id, null, format);
	}

	public LocalDateTimeTextField(String id, IModel<LocalDateTime> model)
	{
		this(id, model, DateTimeFormat.fullDateTime());
	}

	public LocalDateTimeTextField(String id, IModel<LocalDateTime> model, DateTimeFormatter format)
	{
		super(id, model, LocalDateTime.class);
		this.formatter = format;
	}

	@Override
	public <C> IConverter<C> getConverter(Class<C> type)
	{
		if (LocalDateTime.class.isAssignableFrom(type))
		{
			return (IConverter<C>) this.converter;
		}
		return super.getConverter(type);
	}
}
